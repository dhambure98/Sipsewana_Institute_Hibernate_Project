package lk.SipsewanaInstitute.bo.custom;

import lk.SipsewanaInstitute.bo.SuperBO;
import lk.SipsewanaInstitute.dto.RegistrationDTO;

import java.util.ArrayList;

public interface RegisterBO extends SuperBO {
    boolean saveRegister(RegistrationDTO dto) throws Exception;

    boolean updateRegister(RegistrationDTO dto) throws Exception;

    boolean deleteRegister(String id) throws Exception;

    RegistrationDTO getRegister(String id) throws Exception;

    ArrayList<RegistrationDTO> getAllRegister() throws Exception;

    int newRegNo() throws Exception;

}