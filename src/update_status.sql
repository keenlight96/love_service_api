 USE loveservice;
 DELETE FROM `loveservice`.`status` WHERE (`id` = '7');
 
 INSERT INTO Status (id, name_status, is_active)
VALUES
#     (1, 'active', true),
#     (2, 'block', true),
#     (3, 'register', true),
#     (4, 'wait', true),
    (111, 'inActive', true),
#     (5, 'recevied', true),
#     (6, 'complete', true),
#     (7, 'cancel from wait by user', true),
    (8, 'cancel from wait by ccdv', true),
    (9, 'cancel from recevied by user', true),
    (10, 'cancel from recevied by ccdv', true);
    