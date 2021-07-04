import request from '@/utils/request'

const systemUserApi = {
  Page: '/api/v1/user/page',
  Delete: '/api/v1/user/delete',
  Add: '/api/v1/user/save',
  Edit: '/api/v1/user/edit',
  Get: '/api/v1/user/detail',
  Auth: '/api/v1/user/auth',
  BatchDelete: '/api/v1/user/delete'
}

/**
 * 批量删除角色
 * @param data
 */
export function batchDelete (data) {
  return request({
    url: systemUserApi.BatchDelete,
    method: 'post',
    data: data
  })
}

/**
 * 删除角色
 * @param parameter
 * @returns {AxiosPromise}
 */
export function deleteuser (parameter) {
  return request({
    url: systemUserApi.Delete,
    method: 'post',
    data: parameter
  })
}

/**
 * 编辑权限
 * @param data
 * @returns {AxiosPromise}
 */
export function edituser (data) {
  return request({
    url: systemUserApi.Edit,
    method: 'post',
    data: data
  })
}

/**
 * 根据ID，查询角色详情
 * @param parameter
 * @returns {AxiosPromise}
 */
export function detail (parameter) {
  return request({
    url: systemUserApi.Get,
    method: 'get',
    params: parameter
  })
}

/**
 * 添加角色
 * @param data
 * @returns {AxiosPromise}
 */
export function adduser (data) {
  return request({
    url: systemUserApi.Add,
    method: 'post',
    data: data
  })
}

/**
 * 查询所有角色
 * @param parameter         分页条件，以及查询条件
 * @returns {AxiosPromise}
 */
export function queryPage (parameter) {
  return request({
    url: systemUserApi.Page,
    method: 'get',
    params: parameter
  })
}

/**
 * 用户授权
 * @param data   用户id，以及角色id
 * @returns {AxiosPromise}
 */
export function auth (data) {
  return request({
    url: systemUserApi.Auth,
    method: 'post',
    data: data
  })
}
