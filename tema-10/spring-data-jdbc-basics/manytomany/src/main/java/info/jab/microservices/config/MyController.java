package info.jab.microservices.config;

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

import info.jab.microservices.model.Branch;
import info.jab.microservices.model.Subject;
import info.jab.microservices.repository.BranchTestRepository;
import info.jab.microservices.repository.SubjectTestRepository;

@RestController
@RequestMapping("api")
public class MyController {

	@Autowired
	private SubjectTestRepository subjectRepository;
	@Autowired
	private BranchTestRepository branchRepository;

	@GetMapping("branches/{id}")
	public ResponseEntity<Branch> branchById(@PathVariable("id") String id) {
		return new ResponseEntity<>(branchRepository.findById(Long.valueOf(id)).orElse(null), HttpStatus.OK);
	}

	@GetMapping("branches")
	public ResponseEntity<Iterable<Branch>> getAllBranches() {
		return new ResponseEntity<>(branchRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("subjects")
	public ResponseEntity<Iterable<Subject>> getAllSubjects() {
		return new ResponseEntity<>(subjectRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("subjects/{id}")
	public ResponseEntity<Subject> getid(@PathVariable("id") String id) {
		return new ResponseEntity<>(subjectRepository.findById(Long.valueOf(id)).orElse(null), HttpStatus.OK);
	}

	@PostMapping("branches")
	public ResponseEntity<Branch> purchaseOrder(@RequestBody Branch branch) {
		return new ResponseEntity<>(branchRepository.save(branch), HttpStatus.CREATED);
	}

	@PostMapping("subjects")
	public ResponseEntity<Subject> purchaseOrder(@RequestBody Subject order) {
		return new ResponseEntity<>(subjectRepository.save(order), HttpStatus.CREATED);
	}

	@PutMapping("branches/{id}")
	public ResponseEntity<Branch> updateOrder(@RequestBody Branch branch, @PathVariable("id") String id) {
		return new ResponseEntity<>(branchRepository.save(branch), HttpStatus.OK);
	}

	@PutMapping("subjects/{id}")
	public ResponseEntity<Subject> updateOrder(@RequestBody Subject order, @PathVariable("id") String id) {
		return new ResponseEntity<>(subjectRepository.save(order), HttpStatus.OK);
	}

}
