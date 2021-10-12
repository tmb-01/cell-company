insert into roles(id, role_name)
values (1, 'BRANCHES_MANAGER'),
       (2, 'NUMBER_MANAGER'),
       (3, 'EMPLOYEE_MANAGER'),
       (4, 'HR_MANAGER'),
       (5, 'BRANCH_DIRECTOR'),
       (6, 'DIRECTOR'),
       (7, 'CUSTOMER'),
       (8,'TARIFF_MANAGER');
       (9,'POCKET_MANAGER');

insert into users(password,
                  username,
                  isPhysicalPerson,
                  roles,
                  accountNonExpired,
                  accountNonLocked,
                  credentialsNonExpired,
                  enabled)
values ('admin', 'admin', false, 6, true, true, true, true);