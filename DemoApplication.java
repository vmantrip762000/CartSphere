package com.cartsphere.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String description;
	private Integer parent;
	private Integer ordering;
	private Boolean visibility;
	private Boolean allowAds;

	// Getters and Setters
}

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;

	private String name;
	private String description;
	private String price;
	private Date addDate;
	private String countryMade;
	private String image;
	private String status;
	private Short rating;
	private Boolean approve;

	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private User user;

	private String tags;

	// Getters and Setters
}

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String username;
	private String password;
	private String email;
	private String fullName;
	private int groupId;
	private int trustStatus;
	private int regStatus;
	private Date date;
	private String avatar;

	// Getters and Setters
}

// Repo layer

//category
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

//item
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}

//user repo
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}


// Service layer

// Category Service

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category updateCategory(int id, Category category) {
		Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		existingCategory.setName(category.getName());
		existingCategory.setDescription(category.getDescription());
		// update other fields...
		return categoryRepository.save(existingCategory);
	}

	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
	}
}

// Item Service

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	public Item addItem(Item item) {
		return itemRepository.save(item);
	}

	public Item updateItem(int id, Item item) {
		Item existingItem = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
		existingItem.setName(item.getName());
		// update other fields...
		return itemRepository.save(existingItem);
	}

	public void deleteItem(int id) {
		itemRepository.deleteById(id);
	}
}

// User Service

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(int id, User user) {
		User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		existingUser.setUsername(user.getUsername());
		// update other fields...
		return userRepository.save(existingUser);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}

// Controler Layer

// Category controller
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}

	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
	}
}

// Item controller

@RestController
@RequestMapping("/api/items")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@GetMapping
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@PostMapping
	public Item addItem(@RequestBody Item item) {
		return itemService.addItem(item);
	}

	@PutMapping("/{id}")
	public Item updateItem(@PathVariable int id, @RequestBody Item item) {
		return itemService.updateItem(id, item);
	}

	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable int id) {
		itemService.deleteItem(id);
	}
}

// User Controller
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
}

// Testing

@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;

	@Test
	public void testGetAllCategories() {
		// mock data and test
	}

	@Test
	public void testAddCategory() {
		// mock data and test
	}
}
