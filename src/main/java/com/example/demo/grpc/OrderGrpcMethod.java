package com.example.demo.grpc;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Thing;
import com.example.order.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class OrderGrpcMethod {
    int port = 7080;

    public void orderReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);

        GetResponseOrder response = stub.getOrder(GetRequestOrder.newBuilder().build());
        channel.shutdown();

        System.out.println("Order Report:");
        List<ProtoOrder> orders = response.getOrderList();

        for (ProtoOrder protoOrder: orders) {
            System.out.println(protoOrder);
        }
    }

    public void orderedThingReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);

        GetResponseOrderedThing response = stub.getOrderedThing(GetRequestOrderedThing.newBuilder().build());
        channel.shutdown();

        System.out.println("Ordered Thing Report:");
        List<ProtoOrderedThing> orderedThings = response.getOrderedThingsList();

        for (ProtoOrderedThing protoOrderedThing: orderedThings) {
            System.out.println(protoOrderedThing);
        }
    }



    public void makeOrder(Customer customer, List<Thing> bucketForCustomer){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);

        ProtoCustomer protoCustomer = ProtoCustomer.newBuilder()
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .build();

        for (Thing thing: bucketForCustomer){
            ProtoThing protoThing = ProtoThing.newBuilder()
                    .setName(thing.getName())
                    .setSize(thing.getSize())
                    .setCondition(thing.getCondition())
                    .setPrice(thing.getPrice())
                    .build();
            stub.create(CreateRequest.newBuilder()
                    .addThings(protoThing)
                    .build());
        }
        stub.create(CreateRequest.newBuilder()
                .setCustomer(protoCustomer)
                .build());
        channel.shutdown();

    }

}
