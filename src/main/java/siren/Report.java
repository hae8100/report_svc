package siren;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Report_table")
public class Report {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String status;
        private Integer price;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

}
