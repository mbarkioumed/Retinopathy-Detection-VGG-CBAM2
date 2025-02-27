package org.example.modelbackend;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.9.1)", comments = "Source: image_classifier.proto")
public final class ImageClassifierGrpc {

    private ImageClassifierGrpc() {
    }

    public static final String SERVICE_NAME = "org.example.modelbackend.ImageClassifier";

    // Static method descriptors that strictly reflect the proto.
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
    @java.lang.Deprecated // Use {@link #getClassifyImageMethod()} instead.
    public static final io.grpc.MethodDescriptor<org.example.modelbackend.ImageClassifierOuterClass.ImageRequest, org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> METHOD_CLASSIFY_IMAGE = getClassifyImageMethod();

    private static volatile io.grpc.MethodDescriptor<org.example.modelbackend.ImageClassifierOuterClass.ImageRequest, org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> getClassifyImageMethod;

    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
    public static io.grpc.MethodDescriptor<org.example.modelbackend.ImageClassifierOuterClass.ImageRequest, org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> getClassifyImageMethod() {
        io.grpc.MethodDescriptor<org.example.modelbackend.ImageClassifierOuterClass.ImageRequest, org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> getClassifyImageMethod;
        if ((getClassifyImageMethod = ImageClassifierGrpc.getClassifyImageMethod) == null) {
            synchronized (ImageClassifierGrpc.class) {
                if ((getClassifyImageMethod = ImageClassifierGrpc.getClassifyImageMethod) == null) {
                    ImageClassifierGrpc.getClassifyImageMethod = getClassifyImageMethod = io.grpc.MethodDescriptor.<org.example.modelbackend.ImageClassifierOuterClass.ImageRequest, org.example.modelbackend.ImageClassifierOuterClass.ImageResponse>newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(
                                    "org.example.modelbackend.ImageClassifier", "ClassifyImage"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    org.example.modelbackend.ImageClassifierOuterClass.ImageRequest
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    org.example.modelbackend.ImageClassifierOuterClass.ImageResponse
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new ImageClassifierMethodDescriptorSupplier("ClassifyImage"))
                            .build();
                }
            }
        }
        return getClassifyImageMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static ImageClassifierStub newStub(io.grpc.Channel channel) {
        return new ImageClassifierStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output
     * calls on the service
     */
    public static ImageClassifierBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new ImageClassifierBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the
     * service
     */
    public static ImageClassifierFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new ImageClassifierFutureStub(channel);
    }

    /**
     */
    public static abstract class ImageClassifierImplBase implements io.grpc.BindableService {

        /**
         */
        public void classifyImage(org.example.modelbackend.ImageClassifierOuterClass.ImageRequest request,
                io.grpc.stub.StreamObserver<org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getClassifyImageMethod(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getClassifyImageMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<org.example.modelbackend.ImageClassifierOuterClass.ImageRequest, org.example.modelbackend.ImageClassifierOuterClass.ImageResponse>(
                                            this, METHODID_CLASSIFY_IMAGE)))
                    .build();
        }
    }

    /**
     */
    public static final class ImageClassifierStub extends io.grpc.stub.AbstractStub<ImageClassifierStub> {
        private ImageClassifierStub(io.grpc.Channel channel) {
            super(channel);
        }

        private ImageClassifierStub(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected ImageClassifierStub build(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            return new ImageClassifierStub(channel, callOptions);
        }

        /**
         */
        public void classifyImage(org.example.modelbackend.ImageClassifierOuterClass.ImageRequest request,
                io.grpc.stub.StreamObserver<org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getClassifyImageMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     */
    public static final class ImageClassifierBlockingStub
            extends io.grpc.stub.AbstractStub<ImageClassifierBlockingStub> {
        private ImageClassifierBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private ImageClassifierBlockingStub(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected ImageClassifierBlockingStub build(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            return new ImageClassifierBlockingStub(channel, callOptions);
        }

        /**
         */
        public org.example.modelbackend.ImageClassifierOuterClass.ImageResponse classifyImage(
                org.example.modelbackend.ImageClassifierOuterClass.ImageRequest request) {
            return blockingUnaryCall(
                    getChannel(), getClassifyImageMethod(), getCallOptions(), request);
        }
    }

    /**
     */
    public static final class ImageClassifierFutureStub extends io.grpc.stub.AbstractStub<ImageClassifierFutureStub> {
        private ImageClassifierFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private ImageClassifierFutureStub(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected ImageClassifierFutureStub build(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            return new ImageClassifierFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<org.example.modelbackend.ImageClassifierOuterClass.ImageResponse> classifyImage(
                org.example.modelbackend.ImageClassifierOuterClass.ImageRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getClassifyImageMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_CLASSIFY_IMAGE = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ImageClassifierImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ImageClassifierImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CLASSIFY_IMAGE:
                    serviceImpl.classifyImage((org.example.modelbackend.ImageClassifierOuterClass.ImageRequest) request,
                            (io.grpc.stub.StreamObserver<org.example.modelbackend.ImageClassifierOuterClass.ImageResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    private static abstract class ImageClassifierBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        ImageClassifierBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return org.example.modelbackend.ImageClassifierOuterClass.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("ImageClassifier");
        }
    }

    private static final class ImageClassifierFileDescriptorSupplier
            extends ImageClassifierBaseDescriptorSupplier {
        ImageClassifierFileDescriptorSupplier() {
        }
    }

    private static final class ImageClassifierMethodDescriptorSupplier
            extends ImageClassifierBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        ImageClassifierMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (ImageClassifierGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new ImageClassifierFileDescriptorSupplier())
                            .addMethod(getClassifyImageMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
