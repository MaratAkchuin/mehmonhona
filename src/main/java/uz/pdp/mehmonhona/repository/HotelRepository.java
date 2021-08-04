package uz.pdp.mehmonhona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mehmonhona.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
