package com.example.restapiprojectsem4.service.order;

import com.example.restapiprojectsem4.dto.OrderDTO;
import com.example.restapiprojectsem4.dto.OrderDetailDTO;
import com.example.restapiprojectsem4.entity.*;
import com.example.restapiprojectsem4.exception.NotFoundException;
import com.example.restapiprojectsem4.exception.RequestValidException;
import com.example.restapiprojectsem4.repository.*;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import com.example.restapiprojectsem4.util.HandlerDate;
import com.example.restapiprojectsem4.util.HandlerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @Override
    public Order getCart(int accountId) {
        return orderRepository.getCart(accountId);
    }

    @Override
    public Order getCart1() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Optional<Account> account = accountRepository.findByUsername(principal.getName());
        if(account.isPresent()){
            Order order = orderRepository.getCart(account.get().getId());
            return order;
        }
        return null;
    }

    @Override
    public Order addToCart(int accountId, OrderDetailDTO orderDetailDTO) {
        //tìm sản phẩm theo id
        Optional<Product> product = productRepository.findById(orderDetailDTO.getProductId());
        OrderDetail orderDetail = new OrderDetail();
        //Nếu có set tên và id cho sản phẩm
        if(product.isPresent()){
            OrderDetailId key = new OrderDetailId();
            key.setProductId(product.get().getId());
            orderDetail.setId(key);
            orderDetail.setThumbnailProduct(product.get().getThumbnail());
            orderDetail.setProduct(product.get());
            orderDetail.setProductName(product.get().getName());
            orderDetail.setUnitPrice(product.get().getPrice());
        }
        //set số lượng và giá của sản phầm tại thời điểm bán
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        // Tìm trong db xem ng dùng đã có cart hay chưa
        Order exist = orderRepository.getCart(accountId);
        //trường hợp người dùng đã có cart trong db
        if (exist != null){
            Set<OrderDetail> listOrderDetail = exist.getOrderDetails();
            if (listOrderDetail.size() == 0 ){
                listOrderDetail.add(orderDetail);
                orderDetail.setOrder(exist);
                return orderRepository.save(exist);
            }
            for (OrderDetail od : listOrderDetail) {
                if (od.getProduct().getId() == orderDetailDTO.getProductId()){
                    od.setQuantity(od.getQuantity() + orderDetailDTO.getQuantity());
                    exist.setTotalMoney();
                    return orderRepository.save(exist);
                }
            }
            orderDetail.setOrder(exist);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            listOrderDetail.add(orderDetail);
            exist.setOrderDetails(listOrderDetail);
            exist.setTotalMoney();
            return orderRepository.save(exist);
        }
        //trường hợp chưa có cart trong db
        Order newOrder = new Order();
        Account cartOwner = accountRepository.getById(accountId);
        newOrder.setShoppingCart(true);
        newOrder.setAccount(cartOwner);
        newOrder.setAccountId(cartOwner.getId());
//        Set<Order> orderSet = new HashSet<>();
//        orderSet.add(newOrder);
//        cartOwner.setOrders(orderSet);
        Order orderSaved = orderRepository.save(newOrder);
        orderDetail.setOrder(orderSaved);
        Set<OrderDetail> orderDetails = new HashSet<>();
        orderDetails.add(orderDetail);
        orderSaved.setOrderDetails(orderDetails);
        orderSaved.setTotalMoney();
        return orderRepository.save(orderSaved);
    }

    @Override
    public Order addToCart1(OrderDetailDTO orderDetailDTO) {
        try {
            //tìm sản phẩm theo id
            Optional<Product> product = productRepository.findById(orderDetailDTO.getProductId());
            OrderDetail orderDetail = new OrderDetail();
            //Nếu có set tên và id cho sản phẩm
            if(product.isPresent()){
                OrderDetailId key = new OrderDetailId();
                key.setProductId(product.get().getId());
                orderDetail.setId(key);
                orderDetail.setProductId(product.get().getId());
                orderDetail.setThumbnailProduct(product.get().getThumbnail());
                orderDetail.setProduct(product.get());
                orderDetail.setProductName(product.get().getName());
                orderDetail.setUnitPrice(product.get().getPrice());
            }
            //set số lượng và giá của sản phầm tại thời điểm bán
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            // Tìm trong db xem ng dùng đã có cart hay chưa
            Order exist = getCart1();
            //trường hợp người dùng đã có cart trong db
            if (exist != null){
                orderDetail.setOrderId(exist.getId());
                Set<OrderDetail> listOrderDetail = exist.getOrderDetails();
                if (listOrderDetail.size() == 0 ){
                    listOrderDetail.add(orderDetail);
                    orderDetail.setOrder(exist);
                    exist.setTotalMoney();
                    return orderRepository.save(exist);
                }
                for (OrderDetail od : listOrderDetail) {
                    if (od.getProduct().getId() == orderDetailDTO.getProductId()){
                        od.setQuantity(od.getQuantity() + orderDetailDTO.getQuantity());
                        exist.setTotalMoney();
                        return orderRepository.save(exist);
                    }
                }
            orderDetail.setOrder(exist);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            listOrderDetail.add(orderDetail);
            exist.setOrderDetails(listOrderDetail);
            exist.setTotalMoney();
                return orderRepository.save(exist);
            }
            //trường hợp chưa có cart trong db
            Order newOrder = new Order();
            Principal principal = SecurityContextHolder.getContext().getAuthentication();
            Optional<Account> account = accountRepository.findByUsername(principal.getName());
            Account cartOwner = accountRepository.getById(account.get().getId());
            newOrder.setShoppingCart(true);
            newOrder.setAccount(cartOwner);
            newOrder.setAccountId(cartOwner.getId());
//        Set<Order> orderSet = new HashSet<>();
//        orderSet.add(newOrder);
//        cartOwner.setOrders(orderSet);
            Order orderSaved = orderRepository.save(newOrder);
            orderDetail.setOrder(orderSaved);
            Set<OrderDetail> orderDetails = new HashSet<>();
            orderDetails.add(orderDetail);
            orderSaved.setOrderDetails(orderDetails);
            orderSaved.setTotalMoney();
            return orderRepository.save(orderSaved);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order updateCart(int accountId, Set<OrderDetail> list) {
        Order cart = orderRepository.getCart(accountId);
        if (list.size() == 0){
            return cart;
        }
        cart.setOrderDetails(list);
        return orderRepository.save(cart);
    }

    @Override
    public Order controlValue(int accountId, OrderDetailDTO orderDetailDTO) {
        System.out.println(orderDetailDTO.toString());
        Optional<Product> product = productRepository.findById(orderDetailDTO.getProductId());
        Order cart = orderRepository.getCart(accountId);
        if (product.isPresent() && orderDetailDTO.getIsIncrease() == 1){
            OrderDetailId key = new OrderDetailId();
            key.setOrderId(cart.getId());
            key.setProductId(product.get().getId());

            OrderDetail orderDetail = orderDetailRepository.getById(key);
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
            cart.setTotalMoney();
        }
        if (product.isPresent() && orderDetailDTO.getIsIncrease() == 0){
            OrderDetailId key = new OrderDetailId();
            key.setOrderId(cart.getId());
            key.setProductId(product.get().getId());

            OrderDetail orderDetail = orderDetailRepository.getById(key);
            orderDetail.setQuantity(orderDetail.getQuantity() - 1);
            cart.setTotalMoney();
        }

        return orderRepository.save(cart);
    }

    @Override
    public Order controlValue1(OrderDetailDTO orderDetailDTO) {
        System.out.println(orderDetailDTO.toString());
        Optional<Product> product = productRepository.findById(orderDetailDTO.getProductId());
        Order cart = getCart1();
        if (product.isPresent() && orderDetailDTO.getIsIncrease() == 1){
            OrderDetailId key = new OrderDetailId();
            key.setOrderId(cart.getId());
            key.setProductId(product.get().getId());

            OrderDetail orderDetail = orderDetailRepository.getById(key);
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
            cart.setTotalMoney();
        }
        if (product.isPresent() && orderDetailDTO.getIsIncrease() == 0){
            OrderDetailId key = new OrderDetailId();
            key.setOrderId(cart.getId());
            key.setProductId(product.get().getId());

            OrderDetail orderDetail = orderDetailRepository.getById(key);
            orderDetail.setQuantity(orderDetail.getQuantity() - 1);
            if(orderDetail.getQuantity() < 0){
                orderDetail.setQuantity(0);
            }
            cart.setTotalMoney();
        }

        return orderRepository.save(cart);
    }

    @Override
    public Order removeItem(int accountId, int productId) {
        Order cart = orderRepository.getCart(accountId);
        OrderDetailId orderDetailId = new OrderDetailId(cart.getId(), productId);
        cart.removeOrderDetail(orderDetailRepository.getById(orderDetailId));
        orderDetailRepository.delete(orderDetailRepository.getById(orderDetailId));
        cart.setTotalMoney();
        return orderRepository.save(cart);
    }

    @Override
    public Order removeItem1(int productId) {
        Order cart = getCart1();
        if(cart == null){
            return null;
        }
        OrderDetailId orderDetailId = new OrderDetailId(cart.getId(), productId);
        cart.removeOrderDetail(orderDetailRepository.getById(orderDetailId));
        orderDetailRepository.delete(orderDetailRepository.getById(orderDetailId));
        cart.setTotalMoney();
//        if(cart.getOrderDetails().size() == 0){
//            orderRepository.removeById(cart.getId());
//        }
        return orderRepository.save(cart);
    }

    @Override
    public Order proceedOrder(int accountId, OrderDTO orderDTO) {
        Order cart = orderRepository.getCart(accountId);
        Province province = provinceRepository.getById(orderDTO.getProvinceId());
        District district = districtRepository.getById(orderDTO.getDistrictId());
        Ward ward = wardRepository.getById(orderDTO.getWardId());

        cart.setShipName(orderDTO.getShipName());
        cart.setProvinceId(orderDTO.getProvinceId());
        cart.setDistrictId(orderDTO.getDistrictId());
        cart.setWardId(orderDTO.getWardId());
        cart.setShipAddress(orderDTO.getShipAddress());
        cart.setShipNote(orderDTO.getShipNote());
        cart.setShipPhone(orderDTO.getShipPhone());

        cart.setShoppingCart(false);
        /*
         * Order status
         * 1. PENDING
         * 2. SHIPPING
         * 3. COMPLETE
         * */
        cart.setStatus(1);

        return orderRepository.save(cart);
    }

    @Override
    public Order proceedOrder1(OrderDTO orderDTO) {
        Order cart = getCart1();
        Province province = provinceRepository.getById(orderDTO.getProvinceId());
        District district = districtRepository.getById(orderDTO.getDistrictId());
        Ward ward = wardRepository.getById(orderDTO.getWardId());

        cart.setShipName(orderDTO.getShipName());
        cart.setProvinceId(orderDTO.getProvinceId());
        cart.setDistrictId(orderDTO.getDistrictId());
        cart.setWardId(orderDTO.getWardId());
        cart.setShipAddress(orderDTO.getShipAddress());
        cart.setShipNote(orderDTO.getShipNote());
        cart.setShipPhone(orderDTO.getShipPhone());

        cart.setShoppingCart(false);
        cart.setIsRemoved(1);
        /*
         * Order status
         * 1. PENDING
         * 2. SHIPPING
         * 3. COMPLETE
         * */
        cart.setStatus(0);

        return orderRepository.save(cart);
    }

    @Override
    public Order updateStatus(int id, int status) {
        Order orderExist = orderRepository.findOrderById(id);
        if (orderExist == null) throw new NotFoundException("Order is not found");
        int statusOrder = orderExist.getStatus();
        if (statusOrder == 0 && status == 1) {
            orderExist.setStatus(status);
        } else if (statusOrder == 1 && status == 0) {
            orderExist.setStatus(status);
        } else throw new RequestValidException("Update status fail! please check status update.");
        orderExist.setUpdatedAt(HandlerDate.getCurrentTimeDetail());
        return orderRepository.save(orderExist);
    }

    @Override
    public Order updateCheckOut(int id, boolean isCheckout) {
        Order orderExits = orderRepository.findOrderById(id);
        if(orderExits == null) throw new NotFoundException("Order is not found");
        boolean isCheck = orderExits.isCheckout();
        if(isCheck == true){
            orderExits.setCheckout(isCheckout);
        }else if(isCheck == false){
            orderExits.setCheckout(isCheckout);
        }else throw new RuntimeException("update checkout fail pls check");
        orderExits.setUpdatedAt(HandlerDate.getCurrentTimeDetail());
        return orderRepository.save(orderExits);
    }

    @Override
    public List<Order> getHistoryOrder() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Optional<Account> account = accountRepository.findByUsername(principal.getName());
        if(account.isPresent()){
            List<Order> orders = orderRepository.findOrderByAccount(account.get());
            return orders;
        }
        return null;
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Page findAll(ObjectFilter objectFilter) {
        return orderRepository.findAll(HandlerQuery.creatQuery(objectFilter),
                HandlerQuery.creatPagination(objectFilter.getPage(), objectFilter.getPageSize()));
    }

    @Override
    public Order delete(int id) {
        Order order = orderRepository.findOrderById(id);
        if (order == null) throw new NotFoundException("Order is not found");
        order.setIsRemoved(-1);
        order.setDeletedAt(HandlerDate.getCurrentTimeDetail());
        return orderRepository.save(order);
    }

}
