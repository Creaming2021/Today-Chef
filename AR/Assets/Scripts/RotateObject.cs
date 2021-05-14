using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotateObject : MonoBehaviour
{
  float speed = 20.0f;
  float rotSpeed = 0.3f;

  void Update () {
    transform.Rotate(Vector3.forward * rotSpeed);
  }
}
