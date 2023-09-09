package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private  final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;
    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("orderService.request()"); // 로그 시작.
            orderRepository.save(itemId);
            trace.end(status); // 로그 끝.
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
