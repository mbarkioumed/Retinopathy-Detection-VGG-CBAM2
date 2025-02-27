import torch
import grpc
import image_classifier_pb2
import image_classifier_pb2_grpc
from concurrent import futures
from torchvision import transforms
from PIL import Image
import io
from preprocess import crop_black_background, apply_clahe
import torch.nn.functional as F
from model import get_model,get_attention



# Load the trained PyTorch model (replace with your model path)
model = get_model()
model.eval()

# Define image preprocessing
transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
])





class ImageClassifierServicer(image_classifier_pb2_grpc.ImageClassifierServicer):
    def ClassifyImage(self, request, context):
        # Convert bytes to PIL Image
        image = Image.open(io.BytesIO(request.image_data)).convert("RGB")
        image = crop_black_background(image)  # Apply cropping
        image = apply_clahe(image)            # Apply CLAHE
        tensor = transform(image).unsqueeze(0)

        # Run inference
        with torch.no_grad():
            output, attention_maps = model(tensor)
            probabilities = F.softmax(output, dim=1)
            confidence, predicted = torch.max(probabilities, 1)
            prediction = predicted.item()
            print(output)
            print(probabilities)
            print(confidence)
            print(predicted)
            
        # Convert preprocessed image back to bytes
        image_bytes = io.BytesIO()
        image.save(image_bytes, format='JPEG')
        image_bytes = image_bytes.getvalue()
        
        attention_bytes = io.BytesIO()
        get_attention(attention_maps).savefig(attention_bytes, format='JPEG')
        attention_bytes = attention_bytes.getvalue()

        # Map label index to class name (replace with your actual labels)
        labels = ["normal", "abnormal"]
        label = labels[prediction]

        return image_classifier_pb2.ImageResponse(label=label, confidence=confidence.item(), preprocess_data=image_bytes,attention_data=attention_bytes)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    image_classifier_pb2_grpc.add_ImageClassifierServicer_to_server(ImageClassifierServicer(), server)
    server.add_insecure_port("[::]:50051")
    server.start()
    print("Server started on port 50051")
    server.wait_for_termination()

if __name__ == "__main__":
    serve()
