class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int [] inDegree = new int[numCourses]; 
        HashMap<Integer, List<Integer>> myMap = new HashMap<>();
        for(int i=0; i<prerequisites.length; i++){
            inDegree[prerequisites[i][0]]++;
            myMap.putIfAbsent(prerequisites[i][1], new ArrayList<>());
            myMap.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }   

        //Add First set of Courses that are independent.
        Queue<Integer> myQueue = new LinkedList<>();
        int count = 0;
        for(int i=0; i<numCourses; i++){
            if(inDegree[i] == 0){
                myQueue.add(i);
                count++;
            }
        }

        if(myQueue.isEmpty())
            return false;
        if(count == numCourses)
            return true;
        
        while(!myQueue.isEmpty()){
            int curr = myQueue.poll();
            List<Integer> depList = myMap.get(curr);
            if(depList != null){
            for(Integer dep: depList){
                inDegree[dep]--;
                if(inDegree[dep] == 0){
                    myQueue.add(dep);
                    count++;
                    if(count == numCourses) 
                        return true;
                }
            }
          }
        }
    return false;
    }
}