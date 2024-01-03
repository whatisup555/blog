@RestController
@RequestMapping(value = "/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public Result createResponse(@RequestBody ResponseInfo response) {
        responseService.createResponse(response);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getResponse(@PathVariable Integer id) {
        ResponseInfo response = responseService.getResponse(id);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    public Result updateResponse(@PathVariable Integer id, @RequestBody ResponseInfo response) {
        responseService.updateResponse(id, response);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteResponse(@PathVariable Integer id) {
        responseService.deleteResponse(id);
        return Result.success();
    }

    @GetMapping("/user/{userId}")
    public Result listUserResponses(@PathVariable Integer userId) {
        List<ResponseInfo> responses = responseService.listUserResponses(userId);
        return Result.success(responses);
    }
}