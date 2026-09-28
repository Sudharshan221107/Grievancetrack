package com.example.grievance.track.Repository;
package com.example.grievance.track.Repository;

import com.example.grievance.track.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {

}