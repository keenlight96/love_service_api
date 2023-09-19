package com.repository;

import com.model.Account;
import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.AccountCCDVDTO;
import com.model.dto.UserDTO;
import com.model.dto.UserProfileFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile getByAccount_Id(Long id);

    Optional<UserProfile> getUserProfileByAccount_Id(long id);
    @Query(nativeQuery = true, value = "select * from user_profile  order by views desc limit 5 ")
    List<UserProfile> getTop6HotServiceProviders();
    //filter user_profile
    @Query(nativeQuery = true, value = "select u.id,u.first_name u.last_name,u.birthday,u.gender,u.address,u.views, COUNT(bill.id) AS total_bill" +
            "from user_profile u" +
            "join account on account.id = u.account_id " +
            "join bill on bill.accountccdv_id = account.id " +
            "where (:first_name is null or u.first_name like :first_name) " +
            "and (:last_name is null or u.last_name like :last_name) " +
            "and (:birthday is null or year(u.birthday)= :birthday) " +
            "and (:gender is null or u.gender= :gender) " +
            "and (:address is null or u.address= :address) " +
            "and (:views is null or u.views= :views) " +
            "group by u.id, u.last_name " +
            "order by total_bill :order " )
    List<UserProfileFilterDTO> getAllUserProfileByFilter(@Param("first_name") String first_name,
                                                         @Param("last_name") String last_name,
                                                         @Param("birthday") int birthday,
                                                         @Param("gender") String gender,
                                                         @Param("address") String address,
                                                         @Param("views") long views,
                                                         @Param("order") String order);
    @Query("SELECT new com.model.dto.AccountCCDVDTO(u, a, '', AVG(rev.rating), COUNT( rev.rating), COUNT(DISTINCT b.id)) " +
            "FROM UserProfile u " +
            "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
            "LEFT JOIN Account a ON a.id = u.account.id " +
            "LEFT JOIN Bill b ON b.accountCCDV.id = a.id " +
            "WHERE (u.account.role.id = 3) " +
            "AND (u.account.status.id = 1) " +
            "AND (b.isActive = true) " +
            "AND (b.status.id = 6) " +
            "AND (u.account.isActive = true) " +
            "AND (rev.isActive = true OR rev IS NULL) " +
            "AND (u.gender = 'nam')" +
            "GROUP BY u.id " +
            "ORDER BY COUNT(DISTINCT b.id) DESC")
    List<AccountCCDVDTO> findTop4MaleAccountCCDV();
    @Query("SELECT new com.model.dto.AccountCCDVDTO(u, a, '" +
            "', AVG(rev.rating), COUNT( rev.rating), COUNT(DISTINCT b.id)) " +
            "FROM UserProfile u " +
            "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
            "LEFT JOIN Account a ON a.id = u.account.id " +
            "LEFT JOIN Bill b ON b.accountCCDV.id = a.id " +
            "WHERE (u.account.role.id = 3) " +
            "AND (u.account.status.id = 1) " +
            "AND (b.isActive = true) " +
            "AND (b.status.id = 6) " +
            "AND (u.account.isActive = true) " +
            "AND (rev.isActive = true OR rev IS NULL) " +
            "AND (u.gender = 'nu')" +
            "GROUP BY u.id " +
            "ORDER BY COUNT(DISTINCT b.id) DESC")
    List<AccountCCDVDTO> findTop8FemaleAccountCCDV();

    @Query("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) " +
            "from UserProfile u, in (u.supplies) sup " +
            "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "where (u.account.role.id = 3) and (u.account.status.id = 1) and (sup.isActive = true and sup in (:list)) " +
            "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
            "group by u.id ")
    List<UserDTO> getBySupplies(List<Supply> list);
    Optional<UserProfile> findUserProfileByAccount_Id(long idAccount);
    @Query("SELECT new com.model.dto.UserDTO(u, '', AVG(rev.rating), COUNT(rev.rating)) " +
            "FROM UserProfile u " +
            "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
            "WHERE u.account.role.id = 3 " +
            "AND u.account.status.id = 1 " +
            "AND (u.isActive = true) AND (u.account.isActive = true)" +
            "AND (rev.isActive = true or rev is null)" +
            "AND (:nickname is null OR u.account.nickname like :nickname) " +
            "AND (:zoneParam is null OR u.zone.zone like :zoneParam) " +
            "AND (:genderParam is null OR u.gender like :genderParam) " +
            "AND (:birthdayParam is null OR YEAR(u.birthday) = :birthdayParam) " +
            "GROUP BY u.id")
    List<UserDTO> getAllCCDVByFilter(
            @Param("nickname") String nickname,
            @Param("zoneParam") String zone,
            @Param("genderParam") String gender,
            @Param("birthdayParam") Integer birthday
    );


}