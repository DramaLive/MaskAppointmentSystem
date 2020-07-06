package com.dgut.service;

import com.dgut.domain.Reservation;
import com.dgut.domain.ReservationCustom;
import com.dgut.domain.ReservationVo;

import java.util.List;

public interface ReservationService {

    public Integer reservationCount() throws Exception;

    public List<ReservationVo> findByPaging(Integer toPageNo) throws Exception;

    public List<Reservation> findByName(String name);

    public Integer reservationPassCount();

    public List<ReservationVo> findRecord(Integer toPageNo) throws Exception;

    public void reviewReservation(Integer id) throws Exception;

    public Integer reserveCount() throws Exception;

    public List<ReservationVo> findAllByPaging(Integer toPageNo) throws Exception;

    public void addReservation(ReservationCustom reservationCustom) throws Exception;

    public List<ReservationVo> queryByUser(String name) throws Exception;

    public List<ReservationCustom> findByUser(String name) throws Exception;

    public void cancelApplication(Integer id) throws Exception;
}
