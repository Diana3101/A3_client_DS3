package com.example.demo.grpc;

import com.example.customer.*;
import com.example.demo.entities.Customer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.UUID;

public class CustomerGrpcMethod {
    int port = 7080;
    public void customerReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        customerServiceGrpc.customerServiceBlockingStub stub = customerServiceGrpc.newBlockingStub(channel);

        GetResponse response = stub.get(GetRequest.newBuilder().build());
        channel.shutdown();

        System.out.println("Customer Report:");
        List<ProtoCustomer> customers = response.getCustomersList();

        for (ProtoCustomer protoCustomer: customers) {
            System.out.println(protoCustomer);
        }
    }
    public Customer createCustomer(String firstName, String lastName, String size){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        customerServiceGrpc.customerServiceBlockingStub stub = customerServiceGrpc.newBlockingStub(channel);
        ProtoCustomer protoCustomer = ProtoCustomer.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setSize(size)
                .build();
        stub.create(CreateRequest.newBuilder().setCustomer(protoCustomer).build());
        channel.shutdown();
        Customer customer = new Customer(protoCustomer.getFirstName(), protoCustomer.getLastName(), protoCustomer.getSize());
        return customer;
    }

    public void deleteCustomer(UUID customerId) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        customerServiceGrpc.customerServiceBlockingStub stub = customerServiceGrpc.newBlockingStub(channel);

        stub.delete(DeleteRequest.newBuilder()
                .setCustomerId(customerId.toString())
                .build());
        channel.shutdown();


    }

}
