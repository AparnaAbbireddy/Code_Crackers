 public void bookClass()  
    {  
        int section = 0;
        System.out.print("Please type 1 for First Class or 2 for Economy: ");  
        section = input.nextInt();  
        if (section == 1)  
        {  
            seatClass = Seat.FIRSTCLASS;
            lower = 0;
            upper = 4;                
        }  
        else if (section == 2) 
        {  
            seatClass = Seat.ECONOMY;
            lower = 5;
            upper = 9;
        }           
    }  
