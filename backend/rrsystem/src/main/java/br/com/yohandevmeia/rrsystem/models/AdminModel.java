package br.com.yohandevmeia.rrsystem.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
	private ClientModel client;
	    
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReportModel> reports = new ArrayList<>();

    public AdminModel() {
    }

    public AdminModel(ClientModel client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

	public List<ReportModel> getReports() {
		return reports;
	}

	public void setReports(List<ReportModel> reports) {
		this.reports = reports;
	}
}