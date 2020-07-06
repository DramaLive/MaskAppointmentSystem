package com.dgut.dao;

import com.dgut.domain.PagingVO;
import com.dgut.domain.Reservation;
import com.dgut.domain.ReservationCustom;
import com.dgut.domain.ReservationVo;

import java.util.List;

public interface ReservationMapper {

    public Integer reservationCount();

    public List<ReservationVo> findByPaging(PagingVO pagingVO);

    public List<Reservation> findByName(String name);

    public Integer reservationPassCount();

    public List<ReservationVo> findRecord(PagingVO pagingVO);

    public void reviewReservation(Integer id);

    public Integer reserveCount();

    public List<ReservationVo> findAllByPaging(PagingVO pagingVO);

    public void addReservation(ReservationCustom reservationCustom);

    public List<ReservationVo> queryByUser(String name);

    public List<ReservationCustom> findByUser(String name);

    public void cancelApplication(Integer id);
}
