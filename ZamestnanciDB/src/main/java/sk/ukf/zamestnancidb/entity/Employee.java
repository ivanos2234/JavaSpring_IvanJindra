package sk.ukf.zamestnancidb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "first name is required")
    @Size(min = 2, max = 50, message = "first name is limited to 2 - 50 symbols")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "last name is required")
    @Size(min = 2, max = 50, message = "last name is limited to 2 - 50 symbols")
    private String lastName;

    @Column(name = "birth_date")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @Column(name = "email")
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^(?=.{1,254}$)(?=.{1,64}@)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format or length (max 64 characters before @, max 190 after, total max 254)"
    )
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+421[0-9]{9}$",
            message = "Invalid Slovak phone number format. Expected: +421 followed by 9 digits"
    )
    private String phone;

    @Column(name = "job_title")
    @NotBlank(message = "Job title is required")
    @Size(min = 2, max = 50, message = "Job title is limited to 2 - 50 symbols")
    private String jobTitle;

    @Column(name = "salary")
    @Digits(integer = 10, fraction = 0, message = "Salary must be a whole number with up to 10 digits")
    private Double salary;

    @Column(name = "full_time")
    @Min(value = 0, message = "Full time must be 0 or 1")
    @Max(value = 1, message = "Full time must be 0 or 1")
    private int fullTime;

    public Employee() {}

    public Employee(int id, String firstName, String lastName, LocalDate birthDate, String email, String phone, String jobTitle, double salary, int fullTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.fullTime = fullTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        if (salary == null) {
            return -1;
        }
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getFullTime() {
        return fullTime;
    }

    public void setFullTime(int fullTime) {
        this.fullTime = fullTime;
    }
}
