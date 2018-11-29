using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CreateApiTask.Models
{
    public class EmployeeModel
    {
        public int ID { get; set; }
        public string Job_Description { get; set; }
        public int Working_Hours { get; set; }
        public string Name { get; set; }


        EmployeeModel(String job, int wh, String n)
        {
            job = Job_Description;
            Working_Hours = wh;
            Name = n;
        }
    }
}