let input = document.getElementById("file-upload");
let img = document.getElementById("input-image");
let submit = document.getElementById("submit-button");
let preprocessedImage = document.getElementById("preprocessed-image");
let attentionMaps = document.getElementById("attention-maps");
let labelElement = document.getElementById("label");

input.addEventListener("change", function (event) {
    const file = event.target.files[0];
    if (file && !file.type.startsWith("image/")) {
        alert("Please upload a valid image file (JPEG, PNG, JPG).");
        event.target.value = ""; // Clear the input
        return;
    }
    img.src = URL.createObjectURL(file);
});

submit.addEventListener("click", function (event) {
    event.preventDefault();
    if (!input.files[0]) {
        alert("Please upload a valid image first before submitting.");
        return;
    }

    const formData = new FormData();
    formData.append("image", input.files[0]);

    fetch("http://localhost:8080/predict", {
        method: "POST",
        body: formData,
    })
        .then((response) => response.json())
        .then((data) => {
            console.log("Success:", data);
            // Update the preprocessed image
            preprocessedImage.src =
                "http://localhost:8080" + data.preprocessedImageUrl;
            preprocessedImage.style.visibility = "visible";
            // Update the attention maps image
            attentionMaps.src =
                "http://localhost:8080" + data.attentionImageUrl;
            attentionMaps.style.visibility = "visible";
            // Update the label element
            labelElement.textContent = `Predicted Label: ${
                data.label
            } (Confidence: ${(data.confidence * 100).toFixed(2)}%)`;
        })
        .catch((error) => {
            console.error("Error:", error);
        });
});
