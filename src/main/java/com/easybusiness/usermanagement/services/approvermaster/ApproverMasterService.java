package com.easybusiness.usermanagement.services.approvermaster;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.usermanagement.DTO.ApproverMasterDTO;

public interface ApproverMasterService {

    public ResponseEntity<List<ApproverMasterDTO>> getApproverListByTaskId(String taskId);

    public ResponseEntity<ApproverMasterDTO> getApproverListByTaskDesc(String taskDesc);

    public List<ApproverMasterDTO> getAllDetailsOfApproverMaster();

    public ResponseEntity<ApproverMasterDTO> persistApproveMaster(ApproverMasterDTO approverMasterDTO);

    public List<ApproverMasterDTO> findApproverAuthoritiesOfUser(String userId);

    public Boolean findIfUserIsApproverForTaskId(String userId, String taskId);

    public void deleteApproveMaster(Long taskId);

}
