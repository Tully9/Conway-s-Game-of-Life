public class Main{
        static int mapSize = 0;


        public void checkNeighbours(){

            int count=0;

            // check neighbours, also taking into account bounds
            
            // we must check if cell is on the boundary. ..... 0 is boundary, for x and y, 
            
            //4 neighbours, first check bounds

        
            // change state depending on count
            if(count<2){
                state=0;
            }else if((count==2)||(count==3)){
                state=1;
            }else if(count>3){
                state=0;
            }
        }

        public int getState(){
            return state;
        }

        public void setState(int mortality){
            this.state=mortality;
        }
            
        int[][] plane = new int[50][50]; // Initalise 2D array
        boolean game = false;

        UserInput(plane); // Returns the user's inputted pattern int a 2D array

        boolean stopGame = true;

        while (game) {
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    checkNeighbours(plane[i][j]);
                }
            }

            if (stopGame) {
                game = false;
            }
        }
    }
}
