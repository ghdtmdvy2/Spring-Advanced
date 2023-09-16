package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private  final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;
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
