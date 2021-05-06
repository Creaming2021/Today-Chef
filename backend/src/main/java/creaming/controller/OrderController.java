package creaming.controller;

import creaming.dto.OrderDto;
import creaming.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 전체 조회", description = "주문을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<OrderDto.OrderResponse>> getOrderList(@RequestParam("memberId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderList(id));
    }

    @Operation(summary = "주문 등록", description = "주문을 등록합니다.")
    @PostMapping
    public ResponseEntity<Long> postOrder(@RequestBody OrderDto.PostOrderRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.postOrder(dto));
    }

    @Operation(summary = "주문 취소", description = "주문을 취소합니다.")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
