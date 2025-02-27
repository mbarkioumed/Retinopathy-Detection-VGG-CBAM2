package org.example.entities;
public class ResponseDTO {
    private String label;
    private double confidence;
    private String attentionImageUrl;
    private String preprocessedImageUrl;

    // Default constructor for Jackson
    public ResponseDTO() {}

    public ResponseDTO(String label, double confidence, String preprocessID, String attentionID) {
        this.label = label;
        this.confidence = confidence;
        this.preprocessedImageUrl = "/images/" + preprocessID;
        this.attentionImageUrl = "/images/" + attentionID;
    }

    // Getters and setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }

    public String getAttentionImageUrl() { return attentionImageUrl; }
    public void setAttentionImageUrl(String attentionImageUrl) { this.attentionImageUrl = attentionImageUrl; }

    public String getPreprocessedImageUrl() { return preprocessedImageUrl; }
    public void setPreprocessedImageUrl(String preprocessedImageUrl) { this.preprocessedImageUrl = preprocessedImageUrl; }
}
