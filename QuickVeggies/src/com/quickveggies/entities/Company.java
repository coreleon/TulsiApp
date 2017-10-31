package com.quickveggies.entities;

import com.quickveggies.controller.AccountantManagementPanelController;
import com.quickveggies.db.DatabaseClient;
import com.quickveggies.db.erp.ERPSystem;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class Company {
    
        private static Company currentCompany;

        public static Company getCurrentCompany()
        {
            return currentCompany;
        }
        
        public static Company registerCurrentCompany(Accountant currentAccountant)
        {
            if(currentCompany == null)
            {
                currentCompany = DatabaseClient.getInstance().getCompanyById(currentAccountant.getCompanyId());
                
                if(currentCompany != null)
                    currentCompany.updateAccountantsList();
            }
            return currentCompany;
        }
        
        public static Company registerCurrentCompany(User currentUser)
        {
            if(currentCompany == null)
            {
                currentCompany = DatabaseClient.getInstance().getCompanyByFounderId(currentUser.getId());
                
                if(currentCompany != null)
                    currentCompany.updateAccountantsList();
            }
            return currentCompany;
        }
        
        public static Company registerCurrentCompany(Company newlyCreatedCompany)
        {
            if(currentCompany == null)
            {
                currentCompany = DatabaseClient.getInstance().getCompanyByNameAndAddress(newlyCreatedCompany.getName(), newlyCreatedCompany.getAddress());
                
                if(currentCompany != null)
                    currentCompany.updateAccountantsList();
            }
            return currentCompany;
        }
        
        /*************************************************************
         * ~~~ !!! ~~~              ERP FIELDS             ~~~ !!! ~~~
         *************************************************************/
        private ERPSystem erp;
        private List<Accountant> accountantsList;
        
        public ERPSystem getERPSystem()
        {
            return this.erp;
        }
        
        public Accountant[] getAccountantsList()
        {
            return this.accountantsList.toArray(new Accountant[0]);
        }
        
        private void updateAccountantsList()
        {
            this.accountantsList = Accountant.getAccountantsList(this);
        }
        
        public void updateAccountantsList(User owner, Accountant accountant, boolean addAction)
        {
            if(this.ownedBy(owner))
            {
                
                try
                {
                    if(addAction)
                    {
                        this.accountantsList.add(accountant);
                        AccountantManagementPanelController.getInstance().updateList(accountant, true);
                    }
                    else //means remove accountant
                    {
                        this.accountantsList.remove(accountant);
                        AccountantManagementPanelController.getInstance().updateList(accountant, false);
                    }
                }
                catch(IOException exc)
                {
                    exc.printStackTrace();
                }
            }
        }
        
        public boolean ownedBy(User user) 
        {
            return this.getFounderId() == user.getId();
        }
        
    
        /*************************************************************
         * ~~~ !!! ~~~           DATABASE FIELDS           ~~~ !!! ~~~
         *************************************************************/
	private Integer id;
	private String name;
	private String address;
	private String website;
	private String phone;
	private String email;
	private String industryType;
	private String password;
	private int founderId;
	private final String foundationDate;
	private InputStream logo;

        @Deprecated
        public Company()
        {
            this.foundationDate = null;
        }
        
        public Company(Company company)
        {
            this.id = company.id;
            this.name = company.name;
            this.address = company.address;
            this.website = company.website;
            this.phone = company.phone;
            this.email = company.email;
            this.industryType = company.industryType;
            this.password = company.password;
            this.founderId = company.founderId;
            this.foundationDate = company.foundationDate;
            this.logo = company.logo;
        }
        
        /**
         * THIS SHOULD BE USED FOR RETRIEVING COMPANIES FROM THE DATABASE & CREATING NEW COMPANIES.!!!!
         * @param id
         * @param name
         * @param address
         * @param website
         * @param phone
         * @param email
         * @param industryType
         * @param password
         * @param founderId
         * @param foundationDate
         * @param logo 
         */
        public Company(Integer id, String name, String address, String website, String phone, String email, String industryType, String password, int founderId, String foundationDate, InputStream logo)
        {
            this.id = id;
            this.name = name;
            this.address = address;
            this.website = website;
            this.phone = phone;
            this.email = email;
            this.industryType = industryType;
            this.password = password;
            this.founderId = founderId;
            this.foundationDate = foundationDate;
            this.logo = logo;
        }
        
        /**
         *  FOR COMPANY UPDATES.
         * @param name
         * @param address
         * @param website
         * @param phone
         * @param email
         * @param industryType
         * @param password
         * @param founderId
         * @param foundationDate
         * @param logo 
         */
        public Company(String name, String address, String website, String phone, String email, String industryType, String password, String foundationDate, InputStream logo)
        {
            this.id = -1; // Just a placeholder (not used or affects company update)
            this.name = name;
            this.address = address;
            this.website = website;
            this.phone = phone;
            this.email = email;
            this.industryType = industryType;
            this.password = password;
            this.founderId = -1;  // Just a placeholder (not used or affects company update)
            this.foundationDate = foundationDate;
            this.logo = logo;
        }

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFounderId() {
		return founderId;
	}

        /**
         * THIS METHOD SHOULD NOT BE USED WHILE RETRIEVING A COMPANY FROM THE DATABASE BECAUSE ONLY ADMIN CAN SET THE FOUNDERID.
         * @param founderId
         * @throws Exception 
         */
//	public void setFounderId(int founderId) throws Exception
//        {
//            if(User.getCurrentUser().canEditCompany())
//		this.founderId = founderId;
//            else
//                throw new Exception("Insufficient privileges for user \"" + User.getCurrentUser().getName() + "\" to edit company \"" + this.name + "\" !");
//	}
        
        public String getFoundationDate()
        {
            return this.foundationDate;
        }

	public InputStream getLogo() {
		return logo;
	}

	public void setLogo(InputStream logo) {
		this.logo = logo;
	}
}
