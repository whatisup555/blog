@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        boolean isLoginSuccessful = userService.login(user);
        return isLoginSuccessful ? Result.success() : Result.fail("Login failed");
    }

    @PutMapping("/{id}/phone")
    public Result updatePhone(@PathVariable Integer id, @RequestBody String phone) {
        userService.updatePhone(id, phone);
        return Result.success();
    }

    @PutMapping("/{id}/password")
    public Result updatePassword(@PathVariable Integer id, @RequestBody String password) {
        userService.updatePassword(id, password);
        return Result.success();
    }
}