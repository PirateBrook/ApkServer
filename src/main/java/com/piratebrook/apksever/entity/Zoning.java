/**
 * FileName: Zoning
 * Author:   Administrator
 * Date:     2018/9/3 15:12
 */
package com.piratebrook.apksever.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Administrator
 * @create 2018/9/3
 */
@Entity
@Table(name = "zoning")
@Data
public class Zoning {

    @Id
    String code;

    String value;

    String parent;

    @Column(name = "towncode")
    String townCode;


}
