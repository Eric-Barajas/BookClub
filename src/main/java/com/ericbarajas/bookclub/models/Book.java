package com.ericbarajas.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {

	//-------------------MEMBER VARIABLES---------------//
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
	// MEMBER VARIABLES - dependent on project
		@NotNull
		@Size(min = 5, max = 200)
		private String title;
		
		@NotNull
		@Size(min = 5, max = 200)
		private String author;
		
		@NotNull
		@Size(min = 10, max = 200)
		private String description;

		// This will not allow the createdAt column to be updated after creation
	    	@Column(updatable=false)
	    	@DateTimeFormat(pattern="yyyy-MM-dd")
	    	private Date createdAt;
	    	@DateTimeFormat(pattern="yyyy-MM-dd")
	    	private Date updatedAt;
	//-------------------MEMBER VARIABLES---------------//


	//-----------------ESTABLISH RELATIONSHIP-----------//
	    	@ManyToOne(fetch = FetchType.LAZY)
	        @JoinColumn(name="user_id") 
	        private User creator;
	//-----------------ESTABLISH RELATIONSHIP-----------//

	    
	//--------------------CONSTRUCTORS------------------//
	// EMPTY CONSTRUCTOR
		public Book() {
			
		}
	    
	// FULL CONSTRUCTOR
		public Book(@NotNull @Size(min = 5, max = 200) String title, @NotNull @Size(min = 5, max = 200) String author,
				@NotNull @Size(min = 10, max = 200) String description) {
			super();
			this.title = title;
			this.author = author;
			this.description = description;
		}
	//--------------------CONSTRUCTORS------------------//
	    


			//---------GETTERS / SETTERS / OTHER METHODS--------//
	//  ensures our created_at and updated_at get the dates that it needs (without will get no values)
	    	@PrePersist
	    	protected void onCreate(){
	    		this.createdAt = new Date();
	    	}
	    	@PreUpdate
	    	protected void onUpdate(){
	    		this.updatedAt = new Date();
	    	}
	//---------GETTERS / SETTERS / OTHER METHODS--------//

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getAuthor() {
				return author;
			}

			public void setAuthor(String author) {
				this.author = author;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public Date getCreatedAt() {
				return createdAt;
			}

			public void setCreatedAt(Date createdAt) {
				this.createdAt = createdAt;
			}

			public Date getUpdatedAt() {
				return updatedAt;
			}

			public void setUpdatedAt(Date updatedAt) {
				this.updatedAt = updatedAt;
			}

			public User getCreator() {
				return creator;
			}

			public void setCreator(User creator) {
				this.creator = creator;
			}
	    
	    	
}
	

