import { Injectable } from '@angular/core';
import {Example} from './example'
import {Observable} from 'rxjs'
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ExampleService {

  apiURL: string = 'http://localhost:8080/api/jogadores'

  constructor(
    private http : HttpClient
  ) { }
    
  salvar(example: Example) : Observable<Example> {
    return this.http.post<Example>(this.apiURL, example)
  }

  listar() : Observable<Example[]>{
    return this.http.get<Example[]>(this.apiURL);
  }

  deletar(id: number) : Observable<void>{
    const url = `${this.apiURL}/${id}`
    return this.http.delete<void>(url)
  }
  
  concluir(id: number) : Observable<Example>{
    const url = `${this.apiURL}/${id}/done`
    return this.http.patch<Example>(url, {})
  }
}
