package siren;

import siren.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportViewHandler {


    @Autowired
    private ReportRepository reportRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenProductRegistered_then_CREATE_1 (@Payload ProductRegistered productRegistered) {
        try {

            if (!productRegistered.validate()) return;

            // view 객체 생성
            Report report = new Report();
            // view 객체에 이벤트의 Value 를 set 함
            report.setId(productRegistered.getId());
            report.setStatus(productRegistered.getStatus());
            report.setPrice(productRegistered.getPrice());
            // view 레파지 토리에 save
            reportRepository.save(report);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenUpdatedProductStatus_then_UPDATE_1(@Payload UpdatedProductStatus updatedProductStatus) {
        try {
            if (!updatedProductStatus.validate()) return;
                // view 객체 조회
            Optional<Report> reportOptional = reportRepository.findById(updatedProductStatus.getId());
            if( reportOptional.isPresent()) {
                Report report = reportOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                    report.setStatus(updatedProductStatus.getStatus());
                // view 레파지 토리에 save
                reportRepository.save(report);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}