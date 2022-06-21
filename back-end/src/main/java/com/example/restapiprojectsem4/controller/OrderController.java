package com.example.restapiprojectsem4.controller;

import com.example.restapiprojectsem4.dto.OrderDTO;
import com.example.restapiprojectsem4.dto.OrderDetailDTO;
import com.example.restapiprojectsem4.entity.OrderDetail;
import com.example.restapiprojectsem4.repository.OrderDetailRepository;
import com.example.restapiprojectsem4.repository.OrderRepository;
import com.example.restapiprojectsem4.response.RESTPagination;
import com.example.restapiprojectsem4.response.RESTResponse;
import com.example.restapiprojectsem4.service.order.OrderService;
import com.example.restapiprojectsem4.specification.FieldOrder;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import com.example.restapiprojectsem4.util.HandlerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    OrderService service;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public Object testQuery(@RequestParam(name = "name", required = false) String name) {
        System.out.println("name: " + name);
        return orderRepository.findOrderByNameProduct(name, HandlerQuery.creatPagination(1, 15));
    }
    //còn phần list all
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(name = "shipName", required = false) String shipName,
            @RequestParam(name = "nameProduct", required = false) String nameProduct,
            @RequestParam(name = "id", defaultValue = "0") int id,
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "to", required = false) String to,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "accountName", required = false) String accountName,
            @RequestParam(name = "shipPhone", required = false) String shipPhone,
            @RequestParam(name = "minPrice", defaultValue = "0") int minPrice,
            @RequestParam(name = "status", defaultValue = "-1") int statusOrder,
            @RequestParam(name = "phoneAccount", required = false) String phoneAccount,
            @RequestParam(name = "maxPrice", defaultValue = "0") int maxPrice,
            @RequestParam(name = "isRemoved", defaultValue = "1") int isRemoved

    ) {
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withId(id)
                .withPageSize(pageSize).withPage(page)
                .withMaxPrice(maxPrice).withMinPrice(minPrice)
                .withShipPhone(shipPhone).withShipName(shipName).withEmail(email)
                .withName(nameProduct)
                .withPhone(phoneAccount)
                .withStatusO(statusOrder)
                .withAccountName(accountName)
                .withFrom(from).withTo(to).withIsRemoved(isRemoved)
                .withMapField(FieldOrder.createdField())
                .build();
        Page paging = service.findAll(filter);


        return new ResponseEntity<>(new RESTResponse.Success()
                .setPagination(new RESTPagination(paging.getNumber() + 1, paging.getSize(), paging.getTotalElements()))
                .addDatas(paging.getContent())
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity findOrderById(@PathVariable Integer id) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.findById(id))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "status/update")
    public ResponseEntity updateStatus(@Valid
                                       @RequestParam int id,
                                       @RequestParam int status) {

        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.updateStatus(id, status))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "checkOut/update")
    public ResponseEntity updateCheckout(@Valid
                                         @RequestParam int id,
                                         @RequestParam boolean isCheck
    ){
        return new ResponseEntity(new RESTResponse.Success()
                .addData(service.updateCheckOut(id , isCheck))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.delete(id))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "cart")
    public ResponseEntity<Object> getCart(@RequestHeader(name = "accountId") int accountId){
        return new ResponseEntity<>(service.getCart(accountId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "cart1")
    public ResponseEntity<Object> getCart1(){
        return new ResponseEntity<>(service.getCart1(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "addToCart")
    public ResponseEntity<Object> addToCart(@RequestHeader(name = "accountId") int accountId, @RequestBody OrderDetailDTO orderDetailDTO){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.addToCart(accountId,orderDetailDTO))
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "addToCart1")
    public ResponseEntity<Object> addToCart(@RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .addData(service.addToCart1(orderDetailDTO))
                    .buildData(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // hiện tại chưa dùng tới
    @RequestMapping(method = RequestMethod.POST,path = "updateCart")
    public ResponseEntity<Object> updateCart(@RequestHeader(name = "accountId") int accountId, @RequestBody Set<OrderDetail> list){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.updateCart(accountId,list))
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "remove")
    public ResponseEntity<Object> remove(@RequestHeader(name = "accountId") int accountId, @RequestParam(name = "productId") int productId){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.removeItem(accountId,productId))
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "remove1")
    public ResponseEntity<Object> remove(@RequestParam(name = "productId") int productId){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.removeItem1(productId))
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "proceedOrder")
    public ResponseEntity<Object> proceedOrder(@RequestHeader(name = "accountId") int accountId, @RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.proceedOrder(accountId,orderDTO))
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "proceedOrder1")
    public ResponseEntity<Object> proceedOrder(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.proceedOrder1(orderDTO))
                .buildData(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "controlValue")
    public ResponseEntity<Object> controlValue(@RequestHeader(name = "accountId") int accountId, @RequestBody OrderDetailDTO orderDetailDto){
        return new ResponseEntity<>(service.controlValue(accountId,orderDetailDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,path = "controlValue1")
    public ResponseEntity<Object> controlValue(@RequestBody OrderDetailDTO orderDetailDto){
        return new ResponseEntity<>(service.controlValue1(orderDetailDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "orderHistory")
    public ResponseEntity<Object> historyOrder(){
        return new ResponseEntity<>(service.getHistoryOrder(), HttpStatus.OK);
    }
}
