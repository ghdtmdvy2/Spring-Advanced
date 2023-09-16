package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private  final OrderRepositoryV2 orderRepository;
    private final HelloTraceV1 trace;
    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId,"orderService.request()"); // 로그 시작.
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status); // 로그 끝.
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
