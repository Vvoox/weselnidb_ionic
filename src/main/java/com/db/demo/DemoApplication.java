package com.db.demo;

import com.db.demo.Enums.Gender;
import com.db.demo.Enums.Role;
import com.db.demo.Enums.Status;
import com.db.demo.models.Client;
import com.db.demo.models.Demand;
import com.db.demo.models.Driver;
import com.db.demo.models.User;
import com.db.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private DemandRepository demandRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//
//        User user = userRepository.save(User.builder()
//                .name("Khalil")
//                .birthday(LocalDate.now())
//                .cin("EE784596")
//                .city("Maroc")
//                .email("khalil@gmail.com")
//                .lastName("Daoulat")
//                .gender(Gender.MALE)
//                .role(Role.CLIENT)
//                .password("khalil")
//                .phoneNumber("+2126457896")
//                .build());
//
//        User user1 = userRepository.save(User.builder()
//                .name("Khalil")
//                .birthday(LocalDate.now())
//                .cin("EE746100")
//                .city("Maroc")
//                .email("test@gmail.com")
//                .lastName("testos")
//                .gender(Gender.MALE)
//                .role(Role.CLIENT)
//                .password("khalil")
//                .phoneNumber("+212654525")
//                .build());
////
//        Client client = clientRepository.save(Client.builder().user(user).build());
//        Driver driver = driverRepository.save(Driver.builder().user(user1).build());
//        Demand demand = demandRepository.save(Demand.builder()
//                .client(client)
//                .driver(driver)
//                .date(LocalDate.now())
//                .destination("3.01245745,4.0215665")
//                .location("0.13516565,2.26542654265")
//                .price(20.00)
//                .status(Status.ONWAYTOCLIENT)
//                .driverDecision(true)
//                .build());
    }


}
