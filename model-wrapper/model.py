import torch
import torch.nn as nn
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
from torchvision import models
from torchvision.models import VGG16_Weights
from cbam import CBAM  # CBAM attention

# Define the VGG model with CBAM (same definition as in the quantization script)
class VGGWithAttention(nn.Module):
    def __init__(self, num_classes):
        super(VGGWithAttention, self).__init__()
        self.vgg = models.vgg16(weights=VGG16_Weights.IMAGENET1K_V1)

        # Add CBAM after some convolutional layers
        self.cbam1 = CBAM(in_channels=64)
        self.cbam2 = CBAM(in_channels=128)
        self.cbam3 = CBAM(in_channels=256)
        self.cbam4 = CBAM(in_channels=512)

        # Modify the final layer for binary classification
        self.vgg.classifier[6] = nn.Linear(self.vgg.classifier[6].in_features, num_classes)

    def forward(self, x):
        attention_maps = []
        x = self.vgg.features[0:5](x)
        x = self.cbam1(x)
        attention_maps.append(x)
        x = self.vgg.features[5:10](x)
        x = self.cbam2(x)
        attention_maps.append(x)
        x = self.vgg.features[10:17](x)
        x = self.cbam3(x)
        attention_maps.append(x)
        x = self.vgg.features[17:24](x)
        x = self.cbam4(x)
        attention_maps.append(x)
        x = self.vgg.features[24:](x)
        x = self.vgg.avgpool(x)
        x = torch.flatten(x, 1)
        x = self.vgg.classifier(x)
        return x, attention_maps

def get_model():
    # Instantiate the model
    num_classes = 2  # Binary classification
    model = VGGWithAttention(num_classes=num_classes).to('cpu')

    # Apply dynamic quantization (same as in the saving script)
    quantized_model_dynamic = torch.quantization.quantize_dynamic(
        model,
        {nn.Linear},
        dtype=torch.qint8
    )

    # Load the state_dict of the dynamically quantized model
    quantized_model_dynamic.load_state_dict(torch.load('quantized_model.pth', map_location='cpu'))
    return quantized_model_dynamic


def get_attention(attention_maps):
    fig, axs = plt.subplots(1, len(attention_maps), figsize=(20, 5))
    for i, attention_map in enumerate(attention_maps):
        attention_map = attention_map.mean(dim=1).squeeze().cpu().detach().numpy()
        axs[i].imshow(attention_map, cmap='viridis')
        axs[i].axis('off')
    return fig