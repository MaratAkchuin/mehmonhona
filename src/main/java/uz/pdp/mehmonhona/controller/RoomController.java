package uz.pdp.mehmonhona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mehmonhona.entity.Hotel;
import uz.pdp.mehmonhona.entity.Room;
import uz.pdp.mehmonhona.playload.RoomDto;
import uz.pdp.mehmonhona.repository.HotelRepository;
import uz.pdp.mehmonhona.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping
    public List<Room> rooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/{hotelId}")
    public List<Room> rooms(@PathVariable Integer hotelId){
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            return roomRepository.findAllByHotelId(hotelId);
        }
        return null;
    }

    @PostMapping
    public String add(@RequestBody RoomDto roomDto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isPresent()) {
            Room room = new Room(null,roomDto.getNumber(),roomDto.getFloor(),roomDto.getSize(), optionalHotel.get());
            roomRepository.save(room);
            return "room saved";
        }
        return "hotel not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        roomRepository.deleteById(id);
        return "room deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Room room){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            room.setId(id);
            room.setHotel(optionalRoom.get().getHotel());
            roomRepository.save(room);
            return "room edited";
        }
        return "room dot found";
    }
}
