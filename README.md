This project is my solution for the task for project **Compose IDE integration for Kotlin Multiplatform**.

### Instructions
It is necessary to run **mvn clean package** to build the project.
After that, to run the project, it is required to execute **java -jar ComposeIDEIntegrationForKotlinMultiplatformTask-1.0-SNAPSHOT-jar-with-dependencies.jar** from the target folder.

### Visual presentation
- **Before the script has been executed**
  ![not_executed](https://github.com/user-attachments/assets/da9a27b3-27ee-4d0d-8d32-6e8997c36538)

  The image also shows that the keywords are highlighted.
  
- **When the script is executing**
  ![running image](https://github.com/user-attachments/assets/f3abab16-af36-4e25-b00e-01c375a1d0a2)
- **Script executed successfully**
  ![successfully](https://github.com/user-attachments/assets/0e87f7a2-b605-4098-b3e4-7f314a6a828c)
- **Script execution failed**
  ![clickable error](https://github.com/user-attachments/assets/41014642-e14c-48f0-a442-aecfae6f738f)

  When an error occurs during script execution and the error location is clicked, the cursor will automatically
  move to the corresponding line of code in the script.

  **It is necessary to click precisely on the error location, which is styled as a link. Only a click on the link
  itself will trigger the action to move the cursor to the corresponding line in the code. Even a slight deviation
  above or below the link may prevent the cursor from jumping to the correct line.**
  
- **Showing source code of java classes that caused an error in new tab**
  ![error_image](https://github.com/user-attachments/assets/09eabfc9-13d8-4e3e-a339-28d43a4e8186)

  **It is necessary to click precisely on the error location, which is styled as a link. Only a click on the link
  itself will trigger the action to move the cursor to the corresponding line in the code. Even a slight deviation
  above or below the link may prevent the cursor from jumping to the correct line.**

  Additionally, I planned to implement this feature for Kotlin classes as well, including highlighting for both standard
  Java and Kotlin classes. I also intended to implement a more flexible error reading method using the Strategy pattern,
  where different strategies would be applied based on the class that caused the error (such as standard Java classes,
  Kotlin standard classes or our script file). However, I didn't have enough time to complete it.
  



