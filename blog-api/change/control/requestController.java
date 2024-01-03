@RestController
@RequestMapping(value = "/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public Result createRequest(@RequestBody RequestInfo request) {
        requestService.createRequest(request);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getRequest(@PathVariable Integer id) {
        RequestInfo request = requestService.getRequest(id);
        return Result.success(request);
    }

    @PutMapping("/{id}")
    public Result updateRequest(@PathVariable Integer id, @RequestBody RequestInfo request) {
        requestService.updateRequest(id, request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteRequest(@PathVariable Integer id) {
        requestService.deleteRequest(id);
        return Result.success();
    }

    @GetMapping("/user/{userId}")
    public Result listUserRequests(@PathVariable Integer userId) {
        List<RequestInfo> requests = requestService.listUserRequests(userId);
        return Result.success(requests);
    }
}