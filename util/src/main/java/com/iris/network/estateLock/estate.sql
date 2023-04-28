-- 更新地址详情字段
UPDATE `apartment_temp_lock_user_info_lwr`
SET `region_name` = SUBSTRING(`address`, 1, 8),
    `build_name`  = SUBSTRING(`address`, 9, 4),
    `floor_name`  =SUBSTRING(`address`, 13, 3),
    `room_num`    = right(`address`, 4)
where length(`address`) = 39;
UPDATE `apartment_temp_lock_user_info_lwr`
SET `region_name` = SUBSTRING(`address`, 1, 8),
    `build_name`  = SUBSTRING(`address`, 9, 4),
    `floor_name`  =SUBSTRING(`address`, 13, 2),
    `room_num`    = right(`address`, 3)
where length(`address`) = 37;

-- 更新房间id

UPDATE apartment_temp_lock_user_info_lwr lwr
SET lwr.room_id = (
    SELECT room.id
    FROM estate_room room
             LEFT JOIN estate_building build ON room.build_id = build.id
             LEFT JOIN estate_region region ON region.id = build.region_id
    WHERE room.room_num = lwr.room_num
      AND build.build_name = lwr.build_name
      AND region.region_name = lwr.region_name
);


-- 统计信息
SELECT lwr.id,
       lockInfo.device_uuid deviceUUID,
       lwr.lock_user_id,
       lwr.address,
       count(*)
FROM estate_room room
         LEFT JOIN estate_building build ON build.id = room.build_id and build.del_flag = 0
         left join apartment_room_lock_info lockInfo on lockInfo.room_id = room.id
         LEFT JOIN estate_region region ON region.id = build.region_id and region.del_flag = 0
         LEFT JOIN apartment_temp_lock_user_info_lwr lwr ON lwr.region_name = region.`region_name`
    AND lwr.build_name = build.build_name
    AND room.room_num = lwr.room_num
where room.use_type = 9
  and room.del_flag = 0
  -- and lwr.lock_user_id not in (1,2,3)
  and (lwr.psd = "230112" or lwr.psd = "230109" or lwr.psd = "230110")
group by lwr.address
         -- ,lwr.lock_user_id
HAVING count(lwr.address) < 3
order by lwr.id;
