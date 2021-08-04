package uz.pdp.mehmonhona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mehmonhona.entity.Hotel;
import uz.pdp.mehmonhona.repository.HotelRepository;
import uz.pdp.mehmonhona.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> hotels(){
        return hotelRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "hotel saved";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "hotel deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            hotel.setId(id);
            hotelRepository.save(hotel);
            return "hotel edited";
        }
        return "hotel not found";
    }
}
