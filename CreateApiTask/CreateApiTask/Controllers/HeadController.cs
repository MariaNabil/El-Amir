using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CreateApiTask.Models;

namespace CreateApiTask.Controllers
{
    
    
    public class HeadController : ApiController
    {
        EmployeeEntities _ctx = new EmployeeEntities();
        [HttpGet]
        public List<employee> Get(){
         return _ctx.employees.ToList();
        }

        [HttpPost]
        public void Post([FromBody] employee employee)
{
    using (EmployeeEntities entities = new EmployeeEntities())
    {
        entities.employees .Add(employee);
        entities.SaveChanges();
    }
}


        [HttpPut]
        public void Put(int id , [FromBody] employee employee)
        {
            using (EmployeeEntities entities = new EmployeeEntities())
            {
                var entity = _ctx.employees.FirstOrDefault(e => e.ID == id);
                entity.Name = employee.Name;
                entity.Job_Description = employee.Job_Description;
                entity.Working_Hours = employee.Working_Hours;

                entities.SaveChanges();
            }
        }


        // DELETE api/values/5
        public void Delete(int id)
        {
            /*using (EmployeeEntities entities = new EmployeeEntities())
            {
                //entities.employees.RemoveAt(id);


                var item = entities.FirstOrDefault(k => k.ID == id);
                if (item != null)
                { }
               IEnumerable <employee> es= entities.employees.Where(A=> A.ID==id).Select(A => new employee() {
                Working_Hours=A.Working_Hours,
                Job_Description=A.Job_Description,
                Name = A.Name
               
               });
             

               
               using (IEnumerator<employee> iter = es.GetEnumerator())
               {
                   iter.MoveNext();
                   employee e= iter.Current;
                   entities.employees.Remove(e);
                   entities.SaveChanges();
               }

               DataRow drr = dt.AsEnumerable().Where(dr => dr["P"] == id).First();

               
            */
            EmployeeEntities _Ctx= new EmployeeEntities();
                   
            List<employee> emp= _Ctx.employees.Where(A => A.ID == id).ToList();
            if (emp != null && emp.Count!=0)
            {
                _Ctx.employees.Remove(emp[0]);
                _Ctx.SaveChanges();
            }
            }
            
        }
    }

