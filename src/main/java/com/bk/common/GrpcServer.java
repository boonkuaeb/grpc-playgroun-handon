package com.bk.common;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;


public class GrpcServer {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServer.class);

    private static Server server;

    private GrpcServer(Server server) {
        GrpcServer.server = server;
    }

    public static GrpcServer create(BindableService... services){
        return create(6565,services);
    }

    public static GrpcServer create(int port, BindableService... services) {
        var builder = ServerBuilder.forPort(port);
        Arrays.stream(services).forEach(builder::addService);
        return new GrpcServer(builder.build());
    }

    public GrpcServer start() {
        var services = server.getServices()
                .stream()
                .map(ServerServiceDefinition::getServiceDescriptor)
                .map(ServiceDescriptor::getName)
                .toList()
                ;
        try {
            server.start();
            logger.info("server started. listening on port: {}, service: {}", server.getPort(),services.toString());
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void await()
    {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        try {
            server.shutdown();
            logger.info("server stopped");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
