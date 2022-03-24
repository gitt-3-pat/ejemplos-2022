package info.jab.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.jab.microservices.model.PurchaseOrder;
import info.jab.microservices.repository.OrderRepository;

@RestController
@RequestMapping("api")
public class OrderController {

	@Autowired
	private OrderRepository repository;

	@GetMapping("orders")
	public ResponseEntity<Iterable<PurchaseOrder>> getall() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("orders/{id}")
	public ResponseEntity<PurchaseOrder> getid(@PathVariable("id") String id) {
		return new ResponseEntity<>(repository.findById(Long.valueOf(id)).orElse(null), HttpStatus.OK);
	}

	@PostMapping("orders")
	public ResponseEntity<PurchaseOrder> purchaseOrder(@RequestBody PurchaseOrder order) {
		return new ResponseEntity<>(repository.save(order), HttpStatus.CREATED);
	}

	@PutMapping("orders/{id}")
	public ResponseEntity<PurchaseOrder> updateOrder(@RequestBody PurchaseOrder order, @PathVariable("id") String id) {
		return new ResponseEntity<>(repository.save(order), HttpStatus.OK);
	}

	//

	@GetMapping("/api2/orders")
	public ResponseEntity<Iterable<PurchaseOrder>> getOrders() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/api2/orders/add")
	public ResponseEntity<PurchaseOrder> addOrder() {

		final PurchaseOrder order = new PurchaseOrder();

		order.addItem(4, "Captain Future Comet Lego set");
		order.addItem(2, "Cute blue angler fish plush toy");
		order.setShippingAddress("N/A");

		final PurchaseOrder saved = repository.save(order);

		//LOG
		repository.getPO().stream().forEach(System.out::println);
		repository.getOI().stream().forEach(System.out::println);

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}
}
