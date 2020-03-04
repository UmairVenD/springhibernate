package com.Model;

import javax.persistence.*;


@Entity
@Table(name = "studentTbl")
public class StudentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;


        public Long getId(){
            return id;
        }

        public void setFirstName(String firstName){
                this.firstName = firstName;
        }

        public String getFirstName(){
                return firstName;
        }

        public void setLastName(String lastName){
                this.lastName = lastName;
        }

        public String getLastName(){
                return lastName;
        }
    }
