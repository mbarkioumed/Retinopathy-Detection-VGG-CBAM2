package org.example.modelbackend;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

import org.example.controller.ImageStorage;
import org.example.entities.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public class GrpcClient {
    public static ResponseDTO run(MultipartFile image) throws IOException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ImageClassifierGrpc.ImageClassifierBlockingStub stub = ImageClassifierGrpc.newBlockingStub(channel);

        // Convert MultipartFile to byte array
        byte[] imageData = image.getBytes();

        // Send request
        ImageClassifierOuterClass.ImageRequest request = ImageClassifierOuterClass.ImageRequest.newBuilder()
                .setImageData(com.google.protobuf.ByteString.copyFrom(imageData))
                .build();

        ImageClassifierOuterClass.ImageResponse response = stub.classifyImage(request);

        System.out.println("Label: " + response.getLabel() );
        System.out.println("Confidence: " + response.getConfidence());
        System.out.println("Attention Image: " + response.getAttentionData() );
        System.out.println("Preprocessed Image: " + response.getPreprocessData() );

        // Convert ByteString fields to byte arrays
        byte[] attentionImage = response.getAttentionData().toByteArray();
        byte[] preprocessedImage = response.getPreprocessData().toByteArray();
        String attentionID = ImageStorage.saveImage(attentionImage);
        String preprocessedID = ImageStorage.saveImage(preprocessedImage);

        channel.shutdown();

        return new ResponseDTO(response.getLabel(),response.getConfidence(),preprocessedID,attentionID);
    }
}
