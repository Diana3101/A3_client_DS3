package com.example.demo.grpc;

import com.example.demo.entities.Seller;
import com.example.demo.entities.Thing;
import com.example.demo.entities.dto.ServeDTO;
import com.example.seller.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;


public class SellerGrpcMethod {
    int port = 7080;
    public void sellerReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        sellerServiceGrpc.sellerServiceBlockingStub stub = sellerServiceGrpc.newBlockingStub(channel);

        GetResponseSeller response = stub.getSellers(GetRequestSeller.newBuilder().build());
        channel.shutdown();

        System.out.println("Seller Report:");
        List<ProtoSeller> sellers = response.getSellersList();

        for (ProtoSeller protoSeller: sellers) {
            System.out.println(protoSeller);
        }
    }

    public List<Thing> thingReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        sellerServiceGrpc.sellerServiceBlockingStub stub = sellerServiceGrpc.newBlockingStub(channel);

        GetResponseThing response = stub.getThings(GetRequestThing.newBuilder().build());
        channel.shutdown();

        System.out.println("Thing Report:");
        List<ProtoThing> things = response.getThingsList();

        List<Thing> thingsForSale = new ArrayList<>();
        for (ProtoThing protoThing: things) {
            Thing thing = new Thing(protoThing.getName(), protoThing.getSize(), protoThing.getCondition(), protoThing.getPrice());
            thingsForSale.add(thing);
            System.out.println(protoThing);
        }
        return thingsForSale;
    }

    public void addThingsOnSite(Seller seller, List<Thing> addedThings, List<Integer> quantities){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        sellerServiceGrpc.sellerServiceBlockingStub stub = sellerServiceGrpc.newBlockingStub(channel);

        ServeDTO serveDTO = new ServeDTO();
        serveDTO.setSeller(seller);
        serveDTO.setThing(addedThings);
        serveDTO.setThingQuantities(quantities);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String serveJsonStr = gson.toJson(serveDTO);

        stub.create(CreateRequest.newBuilder().setServeJson(serveJsonStr).build());
        channel.shutdown();
    }

}
