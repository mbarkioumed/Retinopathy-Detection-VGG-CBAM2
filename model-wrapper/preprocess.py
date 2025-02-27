import os
from PIL import Image
import numpy as np
import cv2

# Cropping
def crop_black_background(image):
    gray = np.array(image.convert('L'))  # grayscale
    _, thresh = cv2.threshold(gray, 10, 255, cv2.THRESH_BINARY)  # Threshold to identify dark pixels
    coords = np.column_stack(np.where(thresh > 0))  # Find coordinates of non-black pixels
    
    if coords.size == 0:  # if  image is all black report error
        return "error"
    
    # Determine the bounding box of the non-black area and crop the image
    x0, y0, x1, y1 = coords[:, 1].min(), coords[:, 0].min(), coords[:, 1].max(), coords[:, 0].max()
    cropped_image = image.crop((x0, y0, x1, y1))
    return cropped_image

# Apply CLAHE
def apply_clahe(image):
    img_np = np.array(image)  # Convert to numpy array
    lab = cv2.cvtColor(img_np, cv2.COLOR_RGB2LAB)  # Convert RGB to LAB color space
    l, a, b = cv2.split(lab)  # Split LAB image into channels
    
    clahe = cv2.createCLAHE(clipLimit=1.0, tileGridSize=(4, 4))  # Create CLAHE object
    cl = clahe.apply(l)  # Apply CLAHE to the L-channel
    
    limg = cv2.merge((cl, a, b))  # Merge enhanced L-channel with a and b channels
    final = cv2.cvtColor(limg, cv2.COLOR_LAB2RGB)  # Convert LAB image back to RGB
    final_image = Image.fromarray(final)  # Convert numpy array back to PIL image
    return final_image