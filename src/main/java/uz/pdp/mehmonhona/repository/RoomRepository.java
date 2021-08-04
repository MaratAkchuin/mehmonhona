package uz.pdp.mehmonhona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mehmonhona.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    List<Room> findAllByHotelId(Integer hotel_id);
}
