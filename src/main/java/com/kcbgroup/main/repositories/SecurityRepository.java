package com.kcbgroup.main.repositories;

import com.kcbgroup.main.models.Security;
import com.kcbgroup.main.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, Long> {

    Security findByStaffNumber(String staffNumber);

    Security findByStaffNumberAndPassword(String staffNumber, String password);
}
