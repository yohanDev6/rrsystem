package br.com.yohandevmeia.rrsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.yohandevmeia.rrsystem.repositories.AdminRepository;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import br.com.yohandevmeia.rrsystem.repositories.EquipmentRepository;
import br.com.yohandevmeia.rrsystem.repositories.ReportRepository;
import br.com.yohandevmeia.rrsystem.repositories.ReservationRepository;
import br.com.yohandevmeia.rrsystem.repositories.RoomRepository;
import br.com.yohandevmeia.rrsystem.services.AdminService;
import br.com.yohandevmeia.rrsystem.services.ClientService;
import br.com.yohandevmeia.rrsystem.services.EquipmentService;
import br.com.yohandevmeia.rrsystem.services.ReportService;
import br.com.yohandevmeia.rrsystem.services.ReservationService;
import br.com.yohandevmeia.rrsystem.services.RoomService;

@Configuration
public class Services {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Bean
    public ClientService clientService() {
        return new ClientService(clientRepository);
    }

    @Bean
    public AdminService adminService() {
        return new AdminService(adminRepository, clientRepository);
    }
    
    @Bean
    public EquipmentService equipmentService() {
    	return new EquipmentService(equipmentRepository, roomRepository);
    }
    
    @Bean
    public RoomService roomService() {
    	return new RoomService(roomRepository);
    }
    
    @Bean
    public ReportService reportService() {
    	return new ReportService(reportRepository);
    }
    
    @Bean
    public ReservationService reservationService() {
    	return new ReservationService(reservationRepository);
    }
}
