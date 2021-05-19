using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;  

public class ChangeScene : MonoBehaviour
{
  public void GoToHome() {  
    SceneManager.LoadScene("01.home");  
  }  
  public void GoToVideo() {  
    SceneManager.LoadScene("02-1.video");  
  }  
  public void GoTo3d() {  
    SceneManager.LoadScene("02-2.3d");  
  }  
}
